import { RoleModel } from "../../shared/models/role.model";

export interface UserModel {
    id?: number;
    firstName: string;
	lastName: string;
    login: string;
    email: string;
	role: RoleModel;
}