export class UserResponse {

  email: string;
  name: string;
  surname: string;
  username: string;
  roles: Array<string> = [];
  subject: string;


  constructor(data?: any) {
    this.email = data ? data.email : null;
    this.name = data ? data.name : null;
    this.surname = data ? data.surname : null;
    this.subject = data ? data.subject : null;
    this.roles = data ? data.roles : null;
    this.username = data ? data.username : null;

  }
}
