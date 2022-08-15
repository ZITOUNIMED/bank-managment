import { PermissionModel } from "./permission.model";

export interface RoleModel {
    id: number;
    code: string;
    label: string;
    permissions: PermissionModel[];
}