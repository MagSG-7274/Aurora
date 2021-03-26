sudo rm -rf /opt/Aurora
cd /opt
git clone https://github.com/MagSG-7274/Aurora.git
cd /opt/Aurora
sudo chmod +x gradlew
./gradlew bootJar
cd /opt/Aurora/build/libs
mv aurora* aurora.jar
java -jar aurora.jar