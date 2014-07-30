#!/bin/sh

/etc/init.d/springseed stop
/sbin/chkconfig --del springseed

#for i in `find /tmp -type d -name '*ehour.war*'`; do
#  rm -rf $i
#done
