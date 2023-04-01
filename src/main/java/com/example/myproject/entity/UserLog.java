package com.example.myproject.entity;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Data
@Table(name = "logs")
public class UserLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "method_name")
    private String methodName;

    @Column(name = "package_name")
    private String packageName;

    @Column(name = "datetime")
    private LocalDateTime dateTime;

    @Column(name = "client_ip")
    private String clientIp;

    @Column(name = "browser_info")
    private String browserInfo;

}
