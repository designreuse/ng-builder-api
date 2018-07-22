<div class="example-container">
  <mat-form-field>
    <input matInput placeholder="Input">
  </mat-form-field>

  <mat-form-field>
    <textarea matInput placeholder="Textarea"></textarea>
  </mat-form-field>

  <mat-form-field>
    <mat-select placeholder="Select">
      <mat-option value="option">Option</mat-option>
    </mat-select>
  </mat-form-field>
</div>

.example-container {
  display: flex;
  flex-direction: column;
}

.example-container > * {
  width: 100%;
}