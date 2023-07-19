import {DnDLocation} from "./DnDLocation";

export interface DnDEvent {
    serial?:string;
    description?: string;
    leadsToMapSerial?: string;
    name?: string;
    location?: DnDLocation;
}
