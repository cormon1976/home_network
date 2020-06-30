resource "digitalocean_droplet" "Hosted-UniFi-Controller" {
    image = "ubuntu-18-04-x64"
    name = "Hosted-UniFi-Controller"
    region = "nyc1"
    size = "s-1vcpu-1gb"
    private_networking = true
    ssh_keys = [
      var.ssh_fingerprint
    ]
  connection {

    host = self.ipv4_address
    user = "root"
    type = "ssh"
    private_key = file(var.pvt_key)
    timeout = "2m"
  }
  provisioner "remote-exec" {
    inline = [
      "export PATH=$PATH:/usr/bin",
      "sudo apt-get update",
      "sudo add-apt-repository universe -y",
      "sudo apt-get install software-properties-common -y",
      "sudo add-apt-repository ppa:certbot/certbot -y",
      "sudo apt-get install certbot python3-certbot-nginx -y",
      "apt-get update; apt-get install ca-certificates wget -y",
      
    ]
  }  
}
resource "digitalocean_firewall" "Hosted-UniFi-Controller" {
  name = "Unifit-firewall-Inbound"

  droplet_ids = [digitalocean_droplet.Hosted-UniFi-Controller.id]

  inbound_rule {
    protocol         = "tcp"
    port_range       = "22"
    source_addresses = ["XX/32"]
   }

  inbound_rule {
    protocol         = "tcp"
    port_range       = "80"
    source_addresses = ["XX/32"]
   }

  inbound_rule {
    protocol         = "tcp"
    port_range       = "443"
    source_addresses = ["XX/32"]
   }

  inbound_rule {
    protocol         = "tcp"
    port_range       = "3478"
    source_addresses = ["XX/32"]
   }

  inbound_rule {
    protocol         = "tcp"
    port_range       = "8080"
    source_addresses = ["XX/32"]
   }

  inbound_rule {
    protocol         = "tcp"
    port_range       = "8443"
    source_addresses = ["XX/32"]
   }     
} 

