import { NgModule } from '@angular/core';

import { PagesComponent } from './pages.component';
import { PagesRoutingModule } from './pages-routing.module';
import { ThemeModule } from '../@theme/theme.module';
import {CommonService} from './services/common.service';
import {ProjectModule} from './project/project.module';
const PAGES_COMPONENTS = [
  PagesComponent,
];

@NgModule({
  imports: [
    PagesRoutingModule,
    ThemeModule,
    ProjectModule
  ],
  
  declarations: [
    ...PAGES_COMPONENTS,
  ],
  providers:[CommonService]
})
export class PagesModule {
}
