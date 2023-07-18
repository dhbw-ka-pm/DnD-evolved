import { EventPair } from "./EventPair";

export interface DnDMap {
    serial?: string;
    description?: string;
    events?: EventPair[];
    imagePath?: string;
    name?: string;
    sizeX?: number;
    sizeY?: number;
}
