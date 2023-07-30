import { isDevMode, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDialogModule } from "@angular/material/dialog";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatGridListModule } from '@angular/material/grid-list';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ServiceWorkerModule } from '@angular/service-worker';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { DeleteDialogComponent } from './delete-dialog/delete-dialog.component';
import { EditEventDialogComponent } from "./edit-event-dialog/edit-event-dialog.component";
import { EditMapDialogComponent } from './edit-map-dialog/edit-map-dialog.component';
import { EventEditingComponent } from './event-editing/event-editing.component';
import { EventEntryComponent } from './event-entry/event-entry.component';
import { ImageUploadDialogComponent } from './image-upload-dialog/image-upload-dialog.component';
import { InteractiveMapComponent } from './interactive-map/interactive-map.component';
import { MapImageViewComponent } from './map-image-view/map-image-view.component';
import { MaterialDesignModule } from './material-design/material-design.module';
import { NavigationComponent } from './navigation/navigation.component';
import { StoryManagementComponent } from './story-management/story-management.component';

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
        ImageUploadDialogComponent,
        DeleteDialogComponent,

    ],
    providers: [],
    bootstrap: [AppComponent],
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
        FormsModule,
        MatSnackBarModule,
        ReactiveFormsModule,
        MatFormFieldModule,
    ]
})
export class AppModule {}
