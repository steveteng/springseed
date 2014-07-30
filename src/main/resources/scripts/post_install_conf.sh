#!/bin/sh
APP_NAME="springseed"
USERID=$APP_NAME

chown -R $USERID:$USERID /opt/nbnco/applications/$APP_NAME

/sbin/chkconfig --add $APP_NAME
/sbin/chkconfig $APP_NAME on
/etc/init.d/$APP_NAME start
