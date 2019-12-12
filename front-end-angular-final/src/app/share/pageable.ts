import { Sort } from './sort';
import { logging } from 'protractor';

export class Pageable {
    sort: Sort;
    offset: Int32Array;
    pageNumber: Int32Array;
    pageSize: Int32Array;
    paged: boolean;
    unpaged: boolean;
}
