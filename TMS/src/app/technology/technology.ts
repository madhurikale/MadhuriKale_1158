export class Technology {
    constructor(public technology_id: number, public technology_name: string,public active: number
       ) {

    }

     toString(): string {
         console.log("in tostring");
        return `${this.technology_id} ${this.technology_name} ${this.active} `;

    }
}