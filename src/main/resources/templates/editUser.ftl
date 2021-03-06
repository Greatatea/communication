<#import "parts/common.ftl" as c>
<#import "parts/profilePicture.ftl" as p>

<@c.page>
  <div class="form-group mt-3">
    <div class="card ml-5" style="width: 202px;">
        <@p.profilePicture profilePic 200 200/>
    </div>
    <form method="post" enctype="multipart/form-data" action="/edit">
      <div class="form-group">
        <label>
          Username:
          <input type="text" name="username" value="${username}">
        </label>
      </div>
      <div class="form-group">
        <div class="custom-file">
          <input type="file" name="profilePic" id="customFile"
            <#if profilePic??>${profilePic}</#if>
          >
          <label class="custom-file-label" for="customFile">Choose file</label>
        </div>
      </div>
      <input type="hidden" name="_csrf" value="${_csrf.token}" />
      <div class="form-group">
        <button type="submit" class="btn btn-primary">Добавить</button>
      </div>
    </form>
  </div>
</@c.page>