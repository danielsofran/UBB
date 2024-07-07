import {User, UserType} from "./user";

export class Sarcina {
    id: number = 0;
    angajat: User = new User(0, "", "", "", "", "", UserType.Angajat);
    cerinta: string = "";
    datetime: Date = new Date();
    completed: boolean = false;

    static deserialize(input: any): Sarcina {
        let sarcina: Sarcina = Object.assign(new Sarcina(), input);
        sarcina.angajat = Object.assign(sarcina.angajat, input['angajat']);
        return sarcina;
    }

    static deserializeArray(input: any): Sarcina[] {
        return input.map(Sarcina.deserialize);
    }
}