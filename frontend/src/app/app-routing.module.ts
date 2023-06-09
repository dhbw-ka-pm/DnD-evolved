import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EventEditingComponent } from './event-editing/event-editing.component';
import { NavigationComponent } from './navigation/navigation.component';
import { StoryManagementComponent } from './story-management/story-management.component';

const routes: Routes = [
  {
    path: 'event-editing', component: EventEditingComponent
  },
  {
    path: 'story-management', component: StoryManagementComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
