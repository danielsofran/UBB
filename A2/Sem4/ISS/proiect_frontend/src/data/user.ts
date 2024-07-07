import {API} from "./static";

export enum UserType {
    Admin = 1,
    Sef,
    Angajat,
}

export class User {
    id: number;
    username: string;
    password: string;
    email: string;
    first_name: string;
    last_name: string;
    type: UserType;

    constructor(id: number, username: string, password: string, email: string, first_name: string, last_name: string, type: UserType) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.type = type;
    }

    public static fromJson(json: any): User {
        //json = json['user'];
        let type = UserType.Angajat;
        if (json.is_superuser)
            type = UserType.Admin;
        else if (json.is_sef)
            type = UserType.Sef;
        return new User(json.id, json.username, json.password, json.email, json.first_name, json.last_name, type);
    }

    public static fromJsonArray(json: any): User[] {
        console.log("Iitial json: ", json)
        return json.map(User.fromJson);
    }

    public static fromJsonArrayPromise(json: any): Promise<User[]> {
        return Promise.resolve(User.fromJsonArray(json));
    }

    public static fromJsonPromise(json: any): Promise<User> {
        return Promise.resolve(User.fromJson(json));
    }

    public static async getUser(): Promise<User|null> {
        return fetch(API+'/user/', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
            credentials: 'include',
        }).catch((err) => {
            return null;
        }).then(response => {
            if(response == null || !response.ok) {
                return null;
            }
            return response.json();
        }).then(data => {
            if (data == null) {
                return null;
            }
            return User.fromJson(data);
        });
    }
}