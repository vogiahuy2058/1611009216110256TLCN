import { JwtResponse } from './jwt-response';

export class ContentLogin {
    statusCode: Int32Array;
    message: string;
    content: JwtResponse;
}
