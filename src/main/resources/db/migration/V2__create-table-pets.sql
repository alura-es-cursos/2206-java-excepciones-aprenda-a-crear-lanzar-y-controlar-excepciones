create table pets(
    id bigint not null auto_increment,
    nombre varchar(100) not null,
    edad int not null,
    tipo varchar(50) not null,
    adoptado boolean not null,
    imagen varchar(200) not null,

    primary key (id)
);
