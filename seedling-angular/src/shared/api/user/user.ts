export class UserCreateDto {
  public id: string;
  public email: string;
  public password: string;
}

export class UserLoginDto {
  email: string;
  password: string;
  tenant: string;
}

export class User {}
