# Welcome to Aurora!

## What is Aurora? 
- Aurora is a secure and robust way of managing your PiHole, using the Aurora REST Api.
- Aurora is allows you to Disable / Enable ad blocking, Whitelist / Blacklist sites with a simple web browser extension.
- [WIP] For Aurora to work properly, you will need to download [this](https://chrome.google.com/webstore/category/extensions) extension, or download and load Extension source code which can be found [here](https://github.com/ivanhrabcak/aurora-extenstion)
- You can also make your own applications!

## Installation

Prerequisites: <br>
 - Java JRE 15 (or later) <br>
 -  PiHole <br>
 -  Git (and GitHub account)
### Installing with convenience script
- Go [here](https://github.com/MagSG-7274/Aurora/releases) and download it. You will need to grant this script execution privileges which you can do with `sudo chmod +x aurora.sh`. 
- Then install aurora with this: `./aurora.sh -i`
- If you want to run this, enter: `./aurora.sh -r`
- [NOTE] You will have to run this script with sudo
- [NOTE] Please run this every time you see a new commit on github
### Downloading precompiled binaries package
- You can run Aurora without any installation just by downloading the precompiled .jar package from [here](https://github.com/MagSG-7274/Aurora/releases) 
- After downloading you will just need to run the package using command: `java -jar precompiledAuroraBinary.jar`
### Compiling source code
- First git clone the repo. `git clone https://github.com/MagSG7274/Aurora.git`
- Grant execution privilege to gradle wrapper. `sudo chmod +x gradlew`
- Execute gradlew with bootJar flag. `./gradlew bootJar`
- Navigate to: *yourAuroraRoot*/build/libs
- Execute your compiled binary with `java -jar compiledAuroraBinary.jar`
