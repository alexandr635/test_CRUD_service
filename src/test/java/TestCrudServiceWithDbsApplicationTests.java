import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.UUID;


@Test(groups = {"smoke"})
class TestCrudServiceWithDbsApplicationTests {

    private ICrudServiceApi iCrudServiceApi;
    private final Data data = new Data();

    @BeforeClass
    public void BeforeClass() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        iCrudServiceApi = retrofit.create(ICrudServiceApi.class);

        this.data.data = "hello";
    }

    @Test(priority = 1)
    public void PostData(){
        try {
            this.data.id = iCrudServiceApi.PostData(data).execute().body();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Test(priority = 2)
    public void GetData() {
        Data data = null;
        try {
            data = iCrudServiceApi.GetDataById(this.data.id).execute().body();
            Assert.assertEquals(data.time != null, true);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Test(priority = 3)
    public void PutData() {
        this.data.data = "new value";
        try {
            Data data = iCrudServiceApi.PutData(this.data).execute().body();
            Assert.assertEquals(data.data, this.data.data);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Test(priority = 4)
    public void DeleteData(){
        try {
            UUID id = iCrudServiceApi.DeleteData(this.data.id).execute().body();
            Assert.assertEquals(data.data, this.data.data);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
