create database payroll
	with owner postgres;

create sequence hibernate_sequence;

alter sequence hibernate_sequence owner to postgres;

create sequence salary_sequence
    increment by 50;

alter sequence salary_sequence owner to postgres;

create table users
(
    username text        not null,
    password varchar(60) not null,
    enabled  boolean     not null,
    constraint users_pkey
        primary key (username)
);

alter table users
    owner to postgres;

create table authorities
(
    username  text        not null,
    authority varchar(50) not null,
    constraint authorities_idx_1
        primary key (username, authority),
    constraint authorities_username_fkey
        foreign key (username) references users
            on update cascade
);

alter table authorities
    owner to postgres;

create table teacher
(
    id                                           integer not null,
    active                                       boolean,
    base_salary                                  integer,
    birth_date                                   date,
    email                                        text,
    employeed_on_date                            date,
    first_name                                   text    not null,
    functional_bonus                             integer,
    is_allowed_for_extra_tax_deductible_expenses boolean,
    last_name                                    text    not null,
    pesel                                        varchar(11),
    seniority_bonus                              integer,
    education                                    text,
    incentive_pay                                integer,
    teacher_type                                 text,
    constraint teacher_pkey
        primary key (id),
    constraint uk_3kv6k1e64a9gylvkn3gnghc2q
        unique (email)
);

alter table teacher
    owner to postgres;

create table employment_history
(
    id                serial not null,
    end_date          date,
    institution_name  text   not null,
    unpaid_leave_days integer,
    start_date        date,
    employee_id       integer,
    constraint employment_history_pkey
        primary key (id),
    constraint employment_history_teacher__fk
        foreign key (employee_id) references teacher
);

alter table employment_history
    owner to postgres;

create table essential_salary
(
    id                                integer not null,
    accident_insurance_contribution   integer,
    base_salary                       integer,
    disability_contribution_employee  integer,
    disability_contribution_payer     integer,
    gross_salary                      integer,
    healthcare_contribution           integer,
    healthcare_contribution_deduction integer,
    income_tax_advance                integer,
    labor_fund                        integer,
    month                             integer,
    pension_contribution_employee     integer,
    pension_contribution_payer        integer,
    sick_pay                          integer,
    sickness_allowance                integer,
    sickness_contribution             integer,
    tax                               integer,
    tax_deductible_expenses           integer,
    year                              integer,
    employee_id                       integer,
    net_salary                        integer,
    functional_bonus                  integer,
    incentive_pay                     integer,
    seniority_bonus                   integer,
    constraint essential_salary_pkey
        primary key (id),
    constraint essential_salary_teacher__fk
        foreign key (employee_id) references teacher
            on update cascade on delete cascade
);

alter table essential_salary
    owner to postgres;

create unique index essential_salary__uindex
    on essential_salary (month, year, employee_id);

create table overtime
(
    id             serial not null,
    month          integer,
    overtime_hours integer,
    year           integer,
    employee_id    integer,
    constraint overtime_pkey
        primary key (id),
    constraint overtime_teacher__fk
        foreign key (employee_id) references teacher
);

alter table overtime
    owner to postgres;

create table overtime_salary
(
    id                                integer not null,
    accident_insurance_contribution   integer,
    base_salary                       integer,
    disability_contribution_employee  integer,
    disability_contribution_payer     integer,
    gross_salary                      integer,
    healthcare_contribution           integer,
    healthcare_contribution_deduction integer,
    income_tax_advance                integer,
    labor_fund                        integer,
    month                             integer,
    pension_contribution_employee     integer,
    pension_contribution_payer        integer,
    sick_pay                          integer,
    sickness_allowance                integer,
    sickness_contribution             integer,
    tax                               integer,
    tax_deductible_expenses           integer,
    year                              integer,
    employee_id                       integer,
    number_of_overtime_hours          integer,
    net_salary                        integer,
    functional_bonus                  integer,
    incentive_pay                     integer,
    seniority_bonus                   integer,
    constraint overtime_salary_pkey
        primary key (id),
    constraint overtime_salary_teacher__fk
        foreign key (employee_id) references teacher
            on update cascade on delete cascade
);

alter table overtime_salary
    owner to postgres;

create unique index overtime_salary__uindex
    on overtime_salary (month, year, employee_id);

create table sick_leave
(
    sickleaveid serial not null,
    end_date    date   not null,
    start_date  date   not null,
    employee_id integer,
    constraint sick_leave_pkey
        primary key (sickleaveid),
    constraint sick_leave_teacher__fk
        foreign key (employee_id) references teacher
            on update cascade on delete cascade
);

alter table sick_leave
    owner to postgres;

create unique index teacher_pesel_uindex
    on teacher (pesel);

create function username_change() returns trigger
    language plpgsql
as
$$
BEGIN
    Update users
    set username = NEW.email
    where username = OLD.email;


    RETURN NEW;
END;
$$;

alter function username_change() owner to postgres;

