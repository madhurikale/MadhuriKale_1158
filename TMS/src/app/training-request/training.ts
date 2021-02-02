
export class Training {
    constructor(public empid: number,public technology_id: number,public trainer_id: number, public start_date: string,public end_date: string,public no_of_trainee: number,public prject_mgr_status:string,public training_cor_status: string,public comment: string
       ) {

    }

     toString(): string {
         console.log("in tostring");
        return `${this.trainer_id} `;

    }
}