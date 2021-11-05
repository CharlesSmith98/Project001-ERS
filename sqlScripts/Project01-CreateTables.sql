CREATE TABLE IF NOT EXISTS ers_reimbursement_status(
	reimb_status_id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	reimb_status varchar(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS ers_reimbursement_type(
	reimb_type_id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	reimb_type varchar(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS ers_user_roles(
	ers_user_role_id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	user_role varchar(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS ers_users(
	ers_users_id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	ers_username varchar(50) UNIQUE NOT NULL,
	ers_password varchar(50) NOT NULL,
	user_first_name varchar(100) NOT NULL,
	user_last_name varchar(100) NOT NULL,
	user_email varchar(150) UNIQUE NOT NULL,
	user_role_id int REFERENCES ers_user_roles(ers_user_role_id)
);

CREATE TABLE IF NOT EXISTS ers_reimbursement(
	reimb_id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	reimb_amount NUMERIC NOT NULL,
	reimb_submitted timestamptz DEFAULT now(),
	reimb_resolved timestamptz,
	reimb_description varchar(250),
	reimb_receipt bytea,
	reimb_author int REFERENCES ers_users(ers_users_id) NOT NULL,
	reimb_resolver int REFERENCES ers_users(ers_users_id),
	reimb_status_id int REFERENCES ers_reimbursement_status(reimb_status_id) DEFAULT 1,
	reimb_type_id int REFERENCES ers_reimbursement_type(reimb_type_id) NOT NULL	
);