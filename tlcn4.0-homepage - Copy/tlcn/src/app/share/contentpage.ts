import { Employeetype } from '../employeetype/employeetype';
import { Pageable } from './pageable';

export class Contentpage {
    statusCode: Int32Array;
    errorMessage: string;
    details: string;
    content: Employeetype[];
    timestamp: Date;
    pageable: Pageable;
    totalElements: Int32Array;
    totalPages: Int32Array;
    listPage: [];
}
