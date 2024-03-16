<b>Project Setup :-</b>
<br>
1. Clone the repo -> <b>git clone https://github.com/YashChouhan1/New-Docker-Springboot.git -b test-branch</b> <br>
2. Change the directory -> <b>cd New-Docker-Springboot</b><br>
3. run command-> <b>mvn clean package</b><br>
4. then, run -> <b>docker-compose up</b>

If encountered, PermissionError: [Errno 13] Permission denied: '/home/user/sample/New-Docker-Springboot/mysql_data/performance_schema' run -> sudo rm -rf mysql_data

