import { DnDLocation } from "./DnDLocation";

export interface DnDMap {
    description?: string;
    events?: DnDLocation[];
    imagePath?: string;
    name?: string;
    serial?: string;
    sizeX?: number;
    sizeY?: number;
}
