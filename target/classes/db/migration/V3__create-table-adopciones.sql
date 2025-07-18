create table adopciones(
    id bigint not null auto_increment,
    tutor_id bigint not null,
    pet_id bigint not null,
    status varchar(50) not null,
    motivo varchar(100) not null,
    justificacion varchar(100),

    primary key (id),
    constraint fk_adopciones_tutor_id foreign key (tutor_id) references tutores (id),
    constraint fk_adopciones_pet_id foreign key (pet_id) references pets (id)
)