
export class Trainer {
    constructor(public trainer_id: number, public trainer_name: string,public mobile: string,public email:string,public technology_id:number,public active: number
       ) {

    }

     toString(): string {
         console.log("in tostring");
        return `${this.trainer_id} ${this.trainer_name} ${this.active} `;

    }
}