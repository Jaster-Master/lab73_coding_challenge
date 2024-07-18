import {ObservationType} from "./observation_type.ts";

export interface TrafficSignObservationDto {
    id: number;
    latitude: number;
    longitude: number;
    heading: number;
    type: ObservationType;
    value: string;
}