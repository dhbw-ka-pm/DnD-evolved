import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EventEditingComponent } from './event-editing/event-editing.component';
import { StoryManagementComponent } from './story-management/story-management.component';
import { InteractiveMapComponent } from './interactive-map/interactive-map.component';

const routes: Routes = [
  { path: 'event-editing', component: EventEditingComponent },
  { path: 'story-management', component: StoryManagementComponent },
  { path: 'map', component: InteractiveMapComponent },
  { path: 'event-editing/:Serial', component: EventEditingComponent, runGuardsAndResolvers: 'always' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { onSameUrlNavigation: 'reload' })],
  exports: [RouterModule]
})
export class AppRoutingModule {}
