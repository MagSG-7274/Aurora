# Welcome to Aurora!

## What is Aurora? 
- Aurora is a secure and robust way of managing your PiHole from a simple extension.
- Aurora is allows you to Disable / Enable ad blocking, Whitelist / Blacklist sites with a simple web browser extension.
- For Aurora to work properly, you will need to download [this](https://chrome.google.com/webstore/category/extensions) extension, or download and load Extension source code which can be found [here](https://github.com/ivanhrabcak/aurora-extenstion)

## Installation

Prerequisites: <br>
 - Java JRE 15 (or later) <br>
 -  PiHole
### Installing with convenience script
- Go [here](https://github.com/MagSG-7274/Aurora/releases) and download it. You will need to grant this script execution privileges which you can do with `sudo chmod +x Install.sh`. 
- Then just execute the script. `./Install.sh`
### Downloading precompiled binaries package
- You can run Aurora without any installation just by downloading the precompiled .jar package from [here](https://github.com/MagSG-7274/Aurora/releases) 
- After downloading you will just need to run the package using command: `java -jar precompiledAuroraBinary.jar`
### Compiling source code
- First git clone the repo. `git clone https://github.com/MagSG7274/Aurora.git`
- Grant execution privilege to gradle wrapper. `sudo chmod +x gradlew`
- Execute gradlew with bootJar flag. `./gradlew bootJar`
- Navigate to: *yourAuroraRoot*/build/libs
- Execute your compiled binary with `java -jar compiledAuroraBinary.jar`
