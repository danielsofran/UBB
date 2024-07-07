export interface Comment{
    id: number;
    userID: number;
    userName?: string;
    causeID: number;
    date: Date;
    message: string;
}