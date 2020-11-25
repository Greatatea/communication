
<div class="card mt-3 mb-3 mx-auto border-0" style="width: 600px">
<#--<div class="card w-50 mb-3 mx-auto border-0">-->
  <div class="form-group mt-3">
    <form method="post" enctype="multipart/form-data">
      <div class="form-group">
        <label for="comment">Comment:</label>
        <textarea class="form-control" rows="3" id="comment" name="text"></textarea>
      </div>
      <div class="form-group">
        <div class="custom-file">
          <input type="file" name="file" id="customFile">
          <label class="custom-file-label" for="customFile">Choose file</label>
        </div>
      </div>
      <input type="hidden" name="_csrf" value="${_csrf.token}" />
      <div class="form-group">
        <button type="submit" class="btn btn-primary">Добавить</button>
      </div>
    </form>
  </div>
</div>