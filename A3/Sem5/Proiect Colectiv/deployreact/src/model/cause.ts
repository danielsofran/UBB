export class Cause {
  id?: number;
  userId: number = -1;
  name: string = "";
  description: string = "";
  causeType: string = "Personal";
  date: Date = new Date();
  deadline: Date = new Date();
  location: string = "";
  moneyTarget: number = 0;
  moneyObtained: number = 0;
  score: number = 0;
}

export interface CausePhoto {
  id: number;
  causeId: number;
}