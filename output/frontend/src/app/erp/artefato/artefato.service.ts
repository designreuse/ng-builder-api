/**
 * The MIT License
 *
 *  Copyright (c) 2018, Lyndon Tavares (integraldominio@gmail.com)
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MessageService, ConfigService, ResourceService } from '../../infra/security';

import { Projeto } from '../projeto/projeto.service';

@Injectable({
  providedIn: 'root'
})
export class ArtefatoService extends ResourceService<Artefato> {

  constructor(
    httpClient: HttpClient,
    messageService: MessageService,
    configService: ConfigService
  ) {
      super(
      httpClient,
      configService.getApiUrl(),
      'artefatos',
      messageService);
  }
}

export class Artefato {
  id: number;
  projeto: Projeto;
  tipo: string;
  nome: string;
  resourceName: string;
  className: string;
  classFolder: string;
  crudEstilo: string;
  paginaHome: boolean;
  templateTs: string;
  templateHtml: string;
  templateCss: string;
}

// usando json-server
// npm install -g json-server
// json-server --watch db.json
