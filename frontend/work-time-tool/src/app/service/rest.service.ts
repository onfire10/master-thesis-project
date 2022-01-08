import {IWorkTime} from "../model/work-time.model";
import {HttpResponse} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {environment} from "../../environments/environment";


type EntityResponseType = HttpResponse<IWorkTime>;

@Injectable({ providedIn: 'root' })
export class RestService {
  public resourceUrl = environment.API_URL;


}
