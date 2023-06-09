import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EventEditingComponent } from './event-editing/event-editing.component';
import { StoryManagementComponent } from './story-management/story-management.component';
import { MapViewComponent } from './map-view/map-view.component';

const routes: Routes = [
  { path: 'event-editing', component: EventEditingComponent },
  { path: 'story-management', component: StoryManagementComponent },
  { path: 'map-view', component: MapViewComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
