
export class Artist {
    id: number = 0;
    name: string = "";

    static deserialize = (json: any): Artist =>
        Object.assign(new Artist(), json);

    static deserializeArray = (json: any): Artist[] =>
        json.map(Artist.deserialize);
}