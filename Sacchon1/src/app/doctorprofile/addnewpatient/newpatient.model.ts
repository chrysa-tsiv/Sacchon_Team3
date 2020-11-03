export interface DoctorAssign {
    name: string;
    username: string;
    password: string;
    specialty: string;
    description: string;
}

export interface NewPatient {
    name: string;
    username: string;
    password: string;
    gender: string;
    dob: string;
    height: number;
    weight: number;
    history: string;
}