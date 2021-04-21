install() {
  sudo rm -rf /opt/Aurora
  cd /opt
  git clone https://github.com/MagSG-7274/Aurora.git
  touch config.json
  cd /opt/Aurora
  sudo chmod +x gradlew
}

run() {
./gradlew clean bootJar
cd /opt/Aurora/build/libs
mv aurora* aurora.jar
java -jar aurora.jar
}

##
# MAIN BODY
##


if [ $1 == "-i" ];
then
  install
elif [ $1 == "-r" ];
then
  run
fi
