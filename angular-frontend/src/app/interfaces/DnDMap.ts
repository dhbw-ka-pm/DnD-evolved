import { EventPair } from "./EventPair";

export interface DnDMap {
    Serial: string;
    Description?: string;
    Events?: EventPair[];
    ImagePath?: string;
    Name: string;
    SizeX?: number;
    SizeY?: number;
}
