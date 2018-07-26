<div fxLayout="row wrap" class="form-group">
  <div fxFlex.gt-sm="100" class="divide">
    <mat-card>
      <mat-card-title>${artefato.nome}</mat-card-title>
      <mat-card-content>



<mat-table #table [dataSource]="dataSource" matSort class="mat-cell">

    <!--- Note that these columns can be defined in any order.
          The actual rendered columns are set as a property on the row definition" -->

  <#list artefato.elementos as e >
    <!-- ID Column -->
    <ng-container matColumnDef="${e.nome}">
      <mat-header-cell *matHeaderCellDef mat-sort-header>${e.rotulo}</mat-header-cell>
      <mat-cell *matCellDef="let row" >{{row.${e.nome}}}</mat-cell>
    </ng-container>
</#list>
  
    <!-- actions -->
    <ng-container matColumnDef="actions">
      <mat-header-cell *matHeaderCellDef >Actions</mat-header-cell>
      <mat-cell *matCellDef="let row"  >
 
        <button mat-mini-fab color="primary"routerLink="/artefato/edit/{{row.id}}">
          <mat-icon aria-label="Edit">edit</mat-icon>
        </button>
        <button  mat-mini-fab   (click)="deleteItem(row)">
          <mat-icon aria-label="Delete">delete</mat-icon>
        </button>

      </mat-cell>
    </ng-container> 

    <mat-header-row *matHeaderRowDef="displayedColumns"></mat-header-row>
    <mat-row *matRowDef="let row; columns: displayedColumns;"></mat-row>
  </mat-table>


 <!-- <div class="no-results" [style.display]="dataSource.length == 0 ? '' : 'none'">
      No results
  </div> -->

  <mat-paginator #paginator
                 [length]="dataSource.length"
                 [pageIndex]="0"
                 [pageSize]="10"
                 [pageSizeOptions]="[5, 10, 25, 100]">
  </mat-paginator>




</mat-card-content>
</mat-card>
</div>
</div>



<div fxLayout="row wrap" class="form-group">
  <div fxFlex.gt-sm="100" class="divide">
    <mat-card>
      <mat-card-title>Adicionar</mat-card-title>
      <mat-card-content>


<form [formGroup]="form" >
  <formly-form
    [form]="form"
    [options]="options"
    [fields]="fields"
    [model]="model">
    <button mat-button (click)="onSubmit(model)" >Salvar</button>
    <button mat-button (click)="options.resetModel()">Limpar</button>
  </formly-form>
</form>


</mat-card-content>
</mat-card>
</div>
</div>
