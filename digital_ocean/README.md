## Introduction

Steps to using Terraform to build out a Unifi Controller on a Digital Ocean Droplet.

#### Step 1:

Get a token from Digital Ocean and assign it as env_var

`export DO_PAT=XX`

#### Step 2

Get an MD5 hash of you public key and assign it to an env_var. This will be copied to the server that you spin up via terraform, allowing you to ssh in with the key. Remove the MD5: string from the start.

`ssh-keygen -E md5 -lf ~/.ssh/y.pub | awk '{print $2}'
MD5:9f:bf:28:b9:03:b2:3c:cc:fd:1b:7b:82:22:ba:a7:f7`

`DO_SSH_FINGERPRINT=9f:bf:28:b9:03:b2:3c:cc:fd:1b:7b:82:22:ba:a7:f7`

####


`terraform init` to pull in provider plugins for Digital Ocean.


#### Run terrafrom to create Droplet

`./terraform apply \
  -var "do_token=${DO_PAT}" \
  -var "pub_key=$HOME/.ssh/y.pub" \
  -var "pvt_key=$HOME/.ssh/y" \
  -var "ssh_fingerprint=${DO_SSH_FINGERPRINT}"`

##### SSh to Box

`ssh -i ~/.ssh/y root@XX`

#### Install Unifi Controller 

`wget https://get.glennr.nl/unifi/install/unifi-5.13.29.sh`

`rm unifi-latest.sh &> /dev/null; wget https://get.glennr.nl/unifi/install/install_latest/unifi-latest.sh && bash unifi-latest.sh`


`./unifi-5.13.29.sh`

#### Setup Unifi via browser

Browse to https://X.X:8443 and run gui for final steps of setup.
