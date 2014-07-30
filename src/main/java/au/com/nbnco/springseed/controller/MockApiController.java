package au.com.nbnco.springseed.controller;

import au.com.nbnco.springseed.data.Hotel;
import au.com.nbnco.springseed.data.Review;
import au.com.nbnco.springseed.utils.Properties;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/mock_api")
public class MockApiController {
    private static final Logger log = LoggerFactory.getLogger(MockApiController.class);

    @Autowired
    private Properties conf;

    // curl -i -X POST http://localhost:8082/mock_api/file_basic -T "{relative file location}"
    @RequestMapping(value = "/file_basic", method = RequestMethod.POST)
    public String postStreamUpload(HttpServletRequest request) throws IOException {

        createTmpDir();

        String filename = "test-file-" + System.currentTimeMillis();
        log.info("Uploading file to: {}", filename);
        File target = new File(conf.getFileLocation() + filename);

        try (InputStream in = request.getInputStream(); OutputStream out = new FileOutputStream(target)) {
            IOUtils.copy(in, out);
        }
        return String.format("Successfully received %s", filename);
    }

    // curl -i -X POST http://localhost:8082/mock_api/file -F "file=@{relative file location}"
    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public String postUpload(@RequestParam("file") MultipartFile file) {
        createTmpDir();
        if (file.isEmpty()) { return "Nothing in file request parameter"; }

        String filename = file.getOriginalFilename();
        File target = new File(conf.getFileLocation() + filename);

        log.info(String.format("Received a file via POST: %s that is of size: %s", filename, file.getSize()));

        if(target.exists()) { return String.format("Failed to write file - %s already exists", filename); }

        try {
            file.transferTo(target);
        } catch (IOException e) {
            return String.format("Failed to write %s to disk due to %s", filename, e.getMessage());
        }
        return String.format("Successfully received %s", filename);
    }

    // curl -i -T "{relative file location}" -X PUT http://localhost:8082/mock_api/file/{file name}
    @RequestMapping(value = "/file/{filename:.+}", method = RequestMethod.PUT)
    public String putUpload(@PathVariable("filename") String filename, HttpEntity<byte[]> requestEntity) {
        createTmpDir();
        String target = conf.getFileLocation() + filename;

        log.info(String.format("Received a file via PUT: %s that is of size: %s", filename, requestEntity.getBody().length));
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(target);
            IOUtils.write(requestEntity.getBody(), fos);
        } catch (IOException e) {
            String failed = String.format("Failed to write file to: %s due to %s", filename, e.getMessage());
            log.error(failed + e.getStackTrace());
            return failed;
        } finally {
            IOUtils.closeQuietly(fos);
        }
        return String.format("Successfully received %s", filename);
    }

    // curl -i -X DELETE http://localhost:8082/mock_api/file/{file name}
    @RequestMapping(value = "/file/{filename:.+}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("filename") String filename) {
        log.info(String.format("Received a request to DELETE %s", filename));
        File target = new File(conf.getFileLocation() + filename);
        if(!target.exists()) {
            return String.format("Request to delete %s failed since it does not exist", filename);
        }
        if(!target.delete()) {
            return String.format("Request to delete %s failed", filename);
        }
        return String.format("Successfully deleted %s", filename);
    }

    // curl -i -X GET http://localhost:8082/mock_api/file/{file name}
    @RequestMapping(value = "/file/{filename:.+}", method = RequestMethod.GET)
    public FileSystemResource getFile(@PathVariable("filename") String filename) {
        File target = new File(conf.getFileLocation() + filename);
        return new FileSystemResource(target);
    }

    // curl -i -X GET http://localhost:8082/mock_api/json
    @RequestMapping(value = "/json", method = RequestMethod.GET)
    public Hotel getJson() {
        Set<Review> reviews = new HashSet<>();
        Review review1 = new Review();
        review1.setDetails("This is an awesome review of nothing much");

        Review review2 = new Review();
        review2.setDetails("This is a very boring review");
        reviews.add(review1);
        reviews.add(review2);

        Hotel tempHotel = new Hotel();
        tempHotel.setAddress("This is the address field for the JSON response");
        tempHotel.setName("JSON Hotel name");
        tempHotel.setId(99L);
        tempHotel.setReviews(reviews);

        return tempHotel;
    }

    private void createTmpDir() {
        new File(conf.getFileLocation()).mkdirs();
    }
}
