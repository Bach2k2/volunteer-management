import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export abstract class BaseService {
  protected baseUrl: string;
  constructor(protected http: HttpClient) {
    this.baseUrl = 'http://localhost:8080';
  }

   protected getHeaders(): HttpHeaders {
    const token = localStorage.getItem('token');
    return new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: token ? `Bearer ${token}` : '',
    });
  }

  protected get<T>(url: string) {
    return this.http.get<T>(`${this.baseUrl}${url}`, {
      headers: this.getHeaders(),
    });
  }

  protected post<T>(url: string, body: any) {
    return this.http.post<T>(`${this.baseUrl}${url}`, body, {
      headers: this.getHeaders(),
    });
  }

  protected put<T>(url: string, body: any) {
    return this.http.put<T>(`${this.baseUrl}${url}`, body, {
      headers: this.getHeaders(),
    });
  }

  protected delete<T>(url: string) {
    return this.http.delete<T>(`${this.baseUrl}${url}`, {
      headers: this.getHeaders(),
    });
  }

}
