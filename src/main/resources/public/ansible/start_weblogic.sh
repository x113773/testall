#!/bin/sh
#
### BEGIN WLS Configration
USER_NAME=$1 #weblogic
PASSWORD=$2 #1234qwer
WEBLOGIC_PATH=$3 #/home/ansel/Oracle/Middleware/Oracle_Home/
DOMAIN_NAME=$4 #base_domain
SERVER_NAME=$5 #AdminServer 
ADMIN_URL=$6 #iiop://localhost:7001/
WAR_FILE_PATH=$7 #/home/ansel/Oracle/Middleware/Oracle_Home/user_projects/domains/base_domain/my_dm/
PROJECT_NAME=$8 #hw

WLS_LIB=${WEBLOGIC_PATH}wlserver/server/lib/
DOMAIN_PATH=${WEBLOGIC_PATH}user_projects/domains/${DOMAIN_NAME}

#使用这个命令得到weblogic对应服务进程的进程号
WLS_PID=`ps -ef|grep java|grep ${SERVER_NAME}|awk '{print $2}'`
### END WLS Configration


#########  Weblogic server start|stop|restart|status
#用于等待进程启停
wait_for_pid () 
{
    try=0
        case "$1" in
            'created')
                while test $try -lt 7 ; do
                printf .
                try=`expr $try + 1`
                sleep 1
                done                        
                WLS_PID=`ps -ef|grep java|grep ${SERVER_NAME}|awk '{print $2}'`                       
                if [ "$WLS_PID" != "" ] ; then
                    try=''                                                  
                fi
            ;;
            'removed')
                while test $try -lt 35 ; do    
                WLS_PID=`ps -ef|grep java|grep ${SERVER_NAME}|awk '{print $2}'`
                if [ "${WLS_PID}" = "" ] ; then
                    try=''
                break
                fi
                printf .
                try=`expr $try + 1`
                sleep 1
                done
            ;;
        esac
}
#移除原有项目
remove_projcet()
{
echo "removing "
REM_RESULT=`java -cp ${WLS_LIB}weblogic.jar weblogic.Deployer -adminurl ${ADMIN_URL} -username ${USER_NAME} \
-password ${PASSWORD} -name ${PROJECT_NAME} -undeploy`
echo "${REM_RESULT}"
}
#部署新项目
add_projcet()
{
echo "adding "
ADD_RESULT=`java -cp ${WLS_LIB}weblogic.jar weblogic.Deployer -adminurl ${ADMIN_URL} -username ${USER_NAME} \
-password ${PASSWORD} -deploy ${WAR_FILE_PATH}${PROJECT_NAME}.war`
echo "${ADD_RESULT}"
}
#如果weblogic未启动，则启动weblogic，然后移除项目
echo "Starting $SERVER_NAME "
    if [ "${WLS_PID}" = "" ] ; then  
        nohup sh $DOMAIN_PATH/bin/startWebLogic.sh &
  	wait_for_pid created 
    	if [ -n "$try" ] ; then
        	  echo " failed "
       	  exit 1
       else
          remove_projcet
	  add_projcet
          echo " done ! "
       exit 0
    	fi 
    else                
         remove_projcet
	add_projcet
    fi                
       


#停止weblogic服务，备用
stop_weblogic () 
{
printf "Terminating $SERVER_NAME "
    if [ "${WLS_PID}" = "" ] ; then
        echo "No pid - $SERVER_NAME is not running !"
    else                    
        kill -9 $WLS_PID
    wait_for_pid removed 
    if [ -n "$try" ] ; then
    echo " failed "
    exit 1                  
    fi
        echo " done ! "
        exit 0
  fi
}

