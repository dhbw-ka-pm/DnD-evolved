import { NgModule, isDevMode } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ServiceWorkerModule } from '@angular/service-worker';
import { MaterialDesignModule } from './material-design/material-design.module';
import { NavigationComponent } from './navigation/navigation.component';
import { StoryManagementComponent } from './story-management/story-management.component';
import { EventEditingComponent } from './event-editing/event-editing.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatCardModule } from '@angular/material/card';
import { MatMenuModule } from '@angular/material/menu';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MapImageViewComponent } from './map-image-view/map-image-view.component';
import { InteractiveMapComponent } from './interactive-map/interactive-map.component';

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    StoryManagementComponent,
    EventEditingComponent,
    DashboardComponent,
    MapImageViewComponent,
    InteractiveMapComponent
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
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
