import {isDevMode, NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {ServiceWorkerModule} from '@angular/service-worker';
import {MaterialDesignModule} from './material-design/material-design.module';
import {NavigationComponent} from './navigation/navigation.component';
import {StoryManagementComponent} from './story-management/story-management.component';
import {EventEditingComponent} from './event-editing/event-editing.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatCardModule} from '@angular/material/card';
import {MatMenuModule} from '@angular/material/menu';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MapImageViewComponent} from './map-image-view/map-image-view.component';
import {InteractiveMapComponent} from './interactive-map/interactive-map.component';
import {HttpClientModule} from '@angular/common/http';
import {EditEventDialogComponent} from './edit-event-dialog/edit-event-dialog.component';
import {MatDialogModule} from "@angular/material/dialog";
import {FormsModule} from '@angular/forms';
import {EventEntryComponent} from './event-entry/event-entry.component';
import {EditMapDialogComponent} from './edit-map-dialog/edit-map-dialog.component';
import {ImageUploadDialogComponent} from './image-upload-dialog/image-upload-dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    StoryManagementComponent,
    EventEditingComponent,
    DashboardComponent,
    MapImageViewComponent,
    InteractiveMapComponent,
    EditEventDialogComponent,
    EventEntryComponent,
    EditMapDialogComponent,
    ImageUploadDialogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialDesignModule,
    ServiceWorkerModule.register('ngsw-worker.js', {
      enabled: !isDevMode(),
      // Register the ServiceWorker as soon as the application is stable
      // or after 30 seconds (whichever comes first).
      registrationStrategy: 'registerWhenStable:30000'
    }),
    MatGridListModule,
    MatCardModule,
    MatMenuModule,
    MatIconModule,
    MatButtonModule,
    HttpClientModule,
    MatDialogModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
