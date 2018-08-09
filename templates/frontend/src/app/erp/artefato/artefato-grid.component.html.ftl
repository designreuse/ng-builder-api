<div id="convert"> 

<div fxLayout="row wrap" class="form-group">
  <div fxFlex.lt-sm="100" >
    <mat-card>
      <mat-card-title>${artefato.nome}</mat-card-title>

      <a mat-raised-button color="accent" routerLink="/${artefato.classFolder}/add" >
        <mat-icon>add</mat-icon>
      </a>
      
      <a mat-raised-button color="accent" (click)="print()" > 
        <mat-icon>print</mat-icon>
      </a>
      
      <a mat-raised-button color="accent" (click)="screenshot()" > 
        <mat-icon>photo_camera</mat-icon>
      </a>
          
				<query-builder [(ngModel)]='query' [config]='currentConfig'>
					<ng-container *queryButtonGroup="let ruleset; let addRule=addRule; let addRuleSet=addRuleSet; let removeRuleSet=removeRuleSet">
						<button mat-icon-button color="primary" (click)="addRule()">
							<mat-icon>add</mat-icon></button>
<!-- 						<button mat-icon-button color="primary" *ngIf="addRuleSet" (click)="addRuleSet()">
							<mat-icon>add_circle_outline</mat-icon></button>
 -->						<button mat-icon-button color="accent" *ngIf="removeRuleSet" (click)="removeRuleSet()">
							<mat-icon>remove_circle_outline</mat-icon></button>
					</ng-container>
					<ng-container *queryRemoveButton="let rule; let removeRule=removeRule">
						<button mat-icon-button color="accent" (click)="removeRule(rule)">
							<mat-icon>remove</mat-icon>
						</button>
					</ng-container>
					<ng-container *querySwitchGroup="let ruleset; let onChange=onChange">
						<mat-radio-group *ngIf="ruleset" [(ngModel)]="ruleset.condition" (ngModelChange)="onChange($event)">
							<mat-radio-button [style.padding.px]="10" value="and">And</mat-radio-button>
							<mat-radio-button [style.padding.px]="10" value="or">Or</mat-radio-button>
						</mat-radio-group>
					</ng-container>
				
<!-- 					<ng-container *queryEntity="let rule; let entities=entities; let onChange=onChange">
						<mat-form-field>
							<mat-select [(ngModel)]="rule.entity" (ngModelChange)="onChange($event, rule)">
								<mat-option *ngFor="let entity of entities" [value]="entity.value">
								{{entity.name}}
								</mat-option>
							</mat-select>
						</mat-form-field>
					</ng-container> -->
					<ng-container *queryField="let rule; let fields=fields; let onChange=onChange; let getFields = getFields">
						<mat-form-field>
							<mat-select [(ngModel)]="rule.field" (ngModelChange)="onChange($event, rule)">
								<mat-option *ngFor="let field of getFields(rule.entity)" [value]="field.value">
									{{ field.name }}
								</mat-option>
							</mat-select>
						</mat-form-field>
					</ng-container>
					<ng-container *queryOperator="let rule; let operators=operators; let onChange=onChange">
						<mat-form-field [style.width.px]="90">
							<mat-select [(ngModel)]="rule.operator" (ngModelChange)="onChange()">
								<mat-option *ngFor="let value of operators" [value]="value">
									{{ value }}
								</mat-option>
							</mat-select>
						</mat-form-field>
					</ng-container>
					<ng-container *queryInput="let rule; type: 'boolean'; let onChange=onChange">
						<mat-checkbox [(ngModel)]="rule.value" (ngModelChange)="onChange()"></mat-checkbox>
					</ng-container>
					<ng-container *queryInput="let rule; let field=field; let options=options; type: 'category'; let onChange=onChange">
						<mat-form-field>
							<mat-select [(ngModel)]="rule.value" (ngModelChange)="onChange()">
								<mat-option *ngFor="let opt of options" [value]="opt.value">
									{{ opt.name }}
								</mat-option>
							</mat-select>
						</mat-form-field>
					</ng-container>
					<ng-container *queryInput="let rule; type: 'date'; let onChange=onChange">
						<mat-form-field>
							<input matInput [matDatepicker]="picker" [(ngModel)]="rule.value" (ngModelChange)="onChange()">
							<mat-datepicker-toggle matSuffix [for]="picker"></mat-datepicker-toggle>
							<mat-datepicker #picker></mat-datepicker>
						</mat-form-field>
					</ng-container>
					<ng-container *queryInput="let rule; let options=options; type: 'multiselect'; let onChange=onChange">
						<mat-form-field [style.width.px]="300">
							<mat-select [(ngModel)]="rule.value" multiple (ngModelChange)="onChange()">
								<mat-option *ngFor="let opt of options" [value]="opt.value">
									{{ opt.name }}
								</mat-option>
							</mat-select>
						</mat-form-field>
					</ng-container>
					<ng-container *queryInput="let rule; let field=field; type: 'number'; let onChange=onChange">
						<mat-form-field [style.width.px]="50">
							<input matInput [(ngModel)]="rule.value" type="number" (ngModelChange)="onChange()">
						</mat-form-field>
					</ng-container>
					<ng-container *queryInput="let rule; let field=field; type: 'string'; let onChange=onChange">
						<mat-form-field>
							<input matInput [(ngModel)]="rule.value" (ngModelChange)="onChange()">
						</mat-form-field>
					</ng-container>
					<ng-container *queryInput="let rule; let field=field; type: 'textarea'; let onChange=onChange">
						<mat-form-field>
							<textarea matInput [(ngModel)]="rule.value" (ngModelChange)="onChange()">
							</textarea>
						</mat-form-field>
					</ng-container>
				</query-builder>          
            
    <mat-card-content>
    <div class="table-header">
        <mat-form-field>
          <input matInput (keyup)="applyFilter($event.target.value)" placeholder="Filter">
        </mat-form-field>
    </div>

	<mat-table #table [dataSource]="dataSource" matSort class="mat-cell">

	    <!-- ID Column -->
	    <ng-container matColumnDef="id">
	      <mat-header-cell *matHeaderCellDef mat-sort-header>Id</mat-header-cell>
	      <mat-cell *matCellDef="let row" >{{row.id}}</mat-cell>
	    </ng-container>
	    
	   <#list artefato.elementos as e >
	    <!-- ID Column -->
	    <ng-container matColumnDef="${e.nome?uncap_first}">
	      <mat-header-cell *matHeaderCellDef mat-sort-header>${e.rotulo}</mat-header-cell>
	      <mat-cell *matCellDef="let row" >{{row.${e.nome?uncap_first}<#if e.selectDB()>?.${e.labelProp}</#if>}}</mat-cell>
	    </ng-container>
	  </#list>
  
    <!-- actions -->
    <ng-container matColumnDef="actions">
      <mat-header-cell *matHeaderCellDef >Actions</mat-header-cell>
      <mat-cell *matCellDef="let row"  >
 
		<button mat-icon-button [matMenuTriggerFor]="moreMenu" aria-label="More"
					(click)="$event.stopPropagation();">
		<mat-icon class="secondary-text">more_vert</mat-icon> 
		</button>
		<mat-menu #moreMenu="matMenu">
			<button mat-menu-item aria-label="edit"routerLink="/${artefato.classFolder}/edit/{{row.id}}">
				<mat-icon>edit</mat-icon>
				<span>Editar</span>
		</button>
		<button mat-menu-item aria-label="remove" (click)="deleteItem(row)">
					<mat-icon>delete</mat-icon>
					<span>Remover</span>
			</button>
 	 	</mat-menu>
 
  		<#--
        <button mat-mini-fab color="primary"routerLink="/${artefato.classFolder}/edit/{{row.id}}">
          <mat-icon aria-label="Edit">edit</mat-icon>
        </button>
        <button  mat-mini-fab   (click)="deleteItem(row)">
          <mat-icon aria-label="Delete">delete</mat-icon>
        </button>
        -->

      </mat-cell>
    </ng-container> 

    <mat-header-row *matHeaderRowDef="displayedColumns"></mat-header-row>
    <mat-row *matRowDef="let row; columns: displayedColumns;"></mat-row>
  </mat-table>


 <!-- <div class="no-results" [style.display]="dataSource.length == 0 ? '' : 'none'">
      No results
  </div> -->

 
  <mat-paginator [pageSizeOptions]="[5, 10, 25, 100]"></mat-paginator>

</mat-card-content>
</mat-card>
</div>
</div>
 
</div>