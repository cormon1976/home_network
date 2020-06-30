firewall {
    all-ping enable
    broadcast-ping disable
    group {
        address-group KEVIN_MACBOOK {
            address 192.168.40.10
            description KEVIN_MACBOOK
        }
        address-group OPEN_DNS_SERVERS {
            address 208.67.220.220
            address 208.67.222.222
            description "OPENDNS SERVERS"
        }
        network-group LAN_NETWORKS {
            description ALL_INTERNAL_NETWORKS
            network 192.168.1.0/24
            network 192.168.20.0/24
            network 192.168.30.0/24
            network 192.168.40.0/24
            network 192.168.50.0/24
        }
    }
    ipv6-receive-redirects disable
    ipv6-src-route disable
    ip-src-route disable
    log-martians enable
    name AHYA {
        default-action drop
    }
    name AHYAA_GUEST_VLAN20 {
        default-action drop
        description GUEST
        rule 10 {
            action accept
            description "allow dns"
            destination {
                port 53
            }
            log enable
            protocol tcp_udp
            source {
                address 192.168.20.0/24
            }
        }
        rule 20 {
            action accept
            description "allow dhcp"
            destination {
                port 67
            }
            log enable
            protocol udp
            source {
                address 192.168.20.0/24
            }
        }
        rule 30 {
            action drop
            description "drop guest to lan"
            destination {
                group {
                    network-group LAN_NETWORKS
                }
            }
            protocol all
            source {
                address 192.168.20.0/24
            }
        }
        rule 40 {
            action accept
            destination {
                port 80,443
            }
            log enable
            protocol tcp
            source {
                address 192.168.20.0/24
            }
        }
        rule 50 {
            action drop
            description "drop guest to ssh"
            destination {
                address 192.168.20.1/24
                port 22
            }
            log enable
            protocol tcp
            source {
                address 192.168.20.0/24
            }
        }
    }
    name AHYAA_KIDS_VLAN50 {
        default-action drop
        description KIDS
        rule 10 {
            action accept
            description "allow dns"
            destination {
                port 53
            }
            log enable
            protocol tcp_udp
            source {
                address 192.168.50.0/24
            }
        }
        rule 20 {
            action accept
            description "allow dhcp"
            destination {
                port 67
            }
            log enable
            protocol udp
            source {
                address 192.168.50.0/24
            }
        }
        rule 30 {
            action drop
            description "drop KIDS to lan"
            destination {
                group {
                    network-group LAN_NETWORKS
                }
            }
            log enable
            protocol all
            source {
                address 192.168.50.0/24
            }
        }
        rule 40 {
            action accept
            description www
            destination {
                port 80,443
            }
            log enable
            protocol tcp
            source {
                address 192.168.50.0/24
            }
        }
        rule 50 {
            action drop
            description "drop KIDS to ssh"
            destination {
                address 192.168.50.1/24
                port 22
            }
            log enable
            protocol tcp
            source {
                address 192.168.50.0/24
            }
        }
    }
    name AHYAA_OFFICE_VLAN40 {
        default-action drop
        description OFFICE
        rule 10 {
            action accept
            description "allow dns"
            destination {
                port 53
            }
            log enable
            protocol tcp_udp
            source {
                address 192.168.40.0/24
            }
        }
        rule 20 {
            action accept
            description "allow dhcp"
            destination {
                port 67
            }
            log enable
            protocol udp
            source {
                address 192.168.40.0/24
            }
        }
        rule 30 {
            action accept
            description "allow Office to lan"
            destination {
                group {
                    network-group LAN_NETWORKS
                }
            }
            log enable
            protocol all
            source {
                address 192.168.40.0/24
            }
        }
        rule 40 {
            action accept
            description www
            destination {
                port 80,443
            }
            log enable
            protocol tcp
            source {
                address 192.168.40.0/24
            }
        }
        rule 50 {
            action drop
            description "drop to router"
            destination {
                address 192.168.40.1/24
                port 22
            }
            log enable
            protocol tcp
            source {
                address 192.168.40.0/24
            }
        }
        rule 51 {
            action accept
            description "kevin_macbook_ssh to Admin and Mgmt Ports"
            destination {
                port 22,8443,8080
            }
            log disable
            protocol tcp
            source {
                group {
                    address-group NETv4_switch0.40
                }
            }
        }
        rule 52 {
            action accept
            description "Phone to Unifi Application"
            destination {
                address 63.183.X.X
            }
            log enable
            protocol tcp
            source {
                address 192.168.40.14
            }
        }
    }
    name AHYAA_VLAN1 {
        default-action drop
        description DEFAULT
        rule 10 {
            action accept
            description "allow dns"
            destination {
                port 53
            }
            log enable
            protocol tcp_udp
            source {
                address 192.168.1.0/24
            }
        }
        rule 20 {
            action accept
            description "allow dhcp"
            destination {
                port 67
            }
            log enable
            protocol udp
            source {
                address 192.168.1.0/24
            }
        }
        rule 40 {
            action accept
            description www
            destination {
                port 80,443
            }
            log enable
            protocol tcp
            source {
                address 192.168.1.0/24
            }
        }
        rule 50 {
            action drop
            description "drop IOT to ssh"
            destination {
                address 192.168.1.1/24
                port 22
            }
            log enable
            protocol tcp
            source {
                address 192.168.1.0/24
            }
        }
    }
    name AHYAA_idIOT_VLAN30 {
        default-action drop
        description GUEST
        rule 10 {
            action accept
            description "allow dns"
            destination {
                port 53
            }
            log enable
            protocol tcp_udp
            source {
                address 192.168.30.0/24
            }
        }
        rule 20 {
            action accept
            description "allow dhcp"
            destination {
                port 67
            }
            log enable
            protocol udp
            source {
                address 192.168.30.0/24
            }
        }
        rule 30 {
            action drop
            description "drop IOT to lan"
            destination {
                group {
                    network-group LAN_NETWORKS
                }
            }
            log enable
            protocol all
            source {
                address 192.168.30.0/24
            }
        }
        rule 40 {
            action accept
            description www
            destination {
                port 80,443
            }
            log enable
            protocol tcp
            source {
                address 192.168.30.0/24
            }
        }
        rule 50 {
            action drop
            description "drop IOT to ssh"
            destination {
                address 192.168.30.1/24
                port 22
            }
            log enable
            protocol tcp
            source {
                address 192.168.30.0/24
            }
        }
    }
    name WAN_IN {
        default-action drop
        description "WAN to internal"
        rule 10 {
            action accept
            description "Allow established/related"
            state {
                established enable
                related enable
            }
        }
        rule 20 {
            action drop
            description "Drop invalid state"
            state {
                invalid enable
            }
        }
    }
    name WAN_LOCAL {
        default-action drop
        description "WAN to router"
        rule 10 {
            action accept
            description "Allow established/related"
            state {
                established enable
                related enable
            }
        }
        rule 20 {
            action drop
            description "Drop invalid state"
            state {
                invalid enable
            }
        }
    }
    options {
        mss-clamp {
            mss 1412
        }
    }
    receive-redirects disable
    send-redirects enable
    source-validation disable
    syn-cookies enable
}
interfaces {
    ethernet eth0 {
        duplex auto
        speed auto
        vif 10 {
            description "Internet (PPPoE)"
            pppoe 0 {
                default-route auto
                firewall {
                    in {
                        name WAN_IN
                    }
                    local {
                        name WAN_LOCAL
                    }
                }
                mtu 1492
                name-server auto
                password XX
                user-id XX@vfieftth.ie
            }
        }
    }
    ethernet eth1 {
        description Local
        duplex auto
        speed auto
    }
    ethernet eth2 {
        description Local
        duplex auto
        speed auto
    }
    ethernet eth3 {
        address dhcp
        description "Backup interface"
        disable
        duplex auto
        speed auto
    }
    ethernet eth4 {
        description Local
        duplex auto
        poe {
            output pthru
        }
        speed auto
    }
    loopback lo {
    }
    switch switch0 {
        description Switch
        mtu 1500
        switch-port {
            interface eth1 {
                vlan {
                    pvid 1
                }
            }
            interface eth2 {
                vlan {
                    pvid 1
                }
            }
            interface eth4 {
                vlan {
                    pvid 1
                    vid 20
                    vid 30
                    vid 40
                    vid 50
                }
            }
            vlan-aware enable
        }
        vif 1 {
            address 192.168.1.1/24
            ip {
                enable-proxy-arp
            }
        }
        vif 20 {
            address 192.168.20.1/24
            firewall {
                in {
                    name AHYAA_GUEST_VLAN20
                }
            }
            ip {
                enable-proxy-arp
            }
        }
        vif 30 {
            address 192.168.30.1/24
            description vlan30
            firewall {
                in {
                    name AHYAA_idIOT_VLAN30
                }
            }
            ip {
                enable-proxy-arp
            }
        }
        vif 40 {
            address 192.168.40.1/24
            description vlan40
            firewall {
                in {
                    name AHYAA_OFFICE_VLAN40
                }
            }
            ip {
                enable-proxy-arp
            }
        }
        vif 50 {
            address 192.168.50.1/25
            description vlan50
            firewall {
                in {
                    name AHYAA_KIDS_VLAN50
                }
            }
            ip {
                enable-proxy-arp
            }
        }
    }
}
service {
    dhcp-server {
        disabled false
        hostfile-update disable
        shared-network-name LAN {
            authoritative enable
            subnet 192.168.1.0/24 {
                default-router 192.168.1.1
                dns-server 192.168.1.1
                lease 86400
                start 192.168.1.38 {
                    stop 192.168.1.243
                }
            }
        }
        shared-network-name vlan20 {
            authoritative disable
            subnet 192.168.20.0/24 {
                default-router 192.168.20.1
                dns-server 192.168.20.1
                lease 86400
                start 192.168.20.10 {
                    stop 192.168.20.100
                }
            }
        }
        shared-network-name vlan30 {
            authoritative disable
            subnet 192.168.30.0/24 {
                default-router 192.168.30.1
                dns-server 192.168.30.1
                lease 86400
                start 192.168.30.10 {
                    stop 192.168.30.100
                }
            }
        }
        shared-network-name vlan40 {
            authoritative disable
            subnet 192.168.40.0/24 {
                default-router 192.168.40.1
                dns-server 192.168.40.1
                lease 86400
                start 192.168.40.10 {
                    stop 192.168.40.100
                }
                static-mapping C02XQ0G2JG5M {
                    ip-address 192.168.40.10
                    mac-address 38:f9:d3:18:2b:87
                }
            }
        }
        shared-network-name vlan50 {
            authoritative disable
            subnet 192.168.50.0/24 {
                default-router 192.168.50.1
                dns-server 192.168.50.1
                lease 86400
                start 192.168.50.10 {
                    stop 192.168.50.100
                }
            }
        }
        static-arp disable
        use-dnsmasq disable
    }
    dns {
        forwarding {
            cache-size 150
            listen-on switch0.20
            listen-on switch0.30
            listen-on switch0.40
            listen-on switch0.50
        }
    }
    gui {
        http-port 80
        https-port 443
        older-ciphers enable
    }
    nat {
        rule 5010 {
            description "masquerade for WAN"
            log disable
            outbound-interface pppoe0
            protocol all
            type masquerade
        }
    }
    ssh {
        port 22
        protocol-version v2
    }
    unms {
        disable
    }
}
system {
    host-name cormon-fw1
    login {
        user cormon-admin {
            authentication {
                encrypted-password XX
                plaintext-password ""
            }
            full-name The_boss
            level admin
        }
    }
    name-server 208.67.220.220
    name-server 208.67.222.222
    ntp {
        server 0.ubnt.pool.ntp.org {
        }
        server 1.ubnt.pool.ntp.org {
        }
        server 2.ubnt.pool.ntp.org {
        }
        server 3.ubnt.pool.ntp.org {
        }
    }
    syslog {
        global {
            facility all {
                level notice
            }
            facility protocols {
                level debug
            }
        }
    }
    time-zone Europe/Dublin
    traffic-analysis {
        dpi enable
        export enable
    }
}


/* Warning: Do not remove the following line. */
/* === vyatta-config-version: "config-management@1:conntrack@1:cron@1:dhcp-relay@1:dhcp-server@4:firewall@5:ipsec@5:nat@3:qos@1:quagga@2:suspend@1:system@4:ubnt-pptp@1:ubnt-udapi-server@1:ubnt-unms@1:ubnt-util@1:vrrp@1:webgui@1:webproxy@1:zone-policy@1" === */
/* Release version: v1.10.7.5127989.181001.1227 */
