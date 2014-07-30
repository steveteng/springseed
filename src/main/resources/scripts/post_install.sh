#!/bin/sh
APP_NAME="springseed"
USERID=$APP_NAME
mkdir -p /var/log/nbn/$APP_NAME
mkdir -p /var/run/$APP_NAME

/usr/bin/id $USERID
if [ $? -ne 0 ]; then
  echo "Creating user for application: $USERID"
  /usr/sbin/useradd $USERID -d /opt/nbnco/applications/$APP_NAME
else
  echo "User $USERID already exists!"
fi

chown -R $USERID:$USERID /var/log/nbn/$APP_NAME
chown -R $USERID:$USERID /var/run/$APP_NAME
chown -R $USERID:$USERID /opt/nbnco/applications/$APP_NAME

#for i in `find /tmp -type d -name '*${APP_NAME}.war*'`; do
#  rm -rf $i
#done
