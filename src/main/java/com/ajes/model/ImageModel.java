package com.ajes.model;


import javax.persistence.*;

@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Table(name = "image_table")
public class ImageModel {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "type")
	private String type;

//	 image bytes can have large lengths so we specify a value
//	 which is more than the default length for picByte column
//	@Column(name = "picByte", length = 65555)

//	private byte[] picByte;

	public ImageModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ImageModel(Long id, String name, String type, String imagePath) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "ImageModel{" +
				"id=" + id +
				", name='" + name + '\'' +
				", type='" + type + '\'' +
				'}';
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

//	public void savePic() throws IOException {
//		File path = new File("d:\\pictures");
//		File filePath = new File(path,name);
//		FileOutputStream fout = new FileOutputStream(filePath);
//		fout.write(picByte);
//		fout.close();
//	}

//	public void getPic() throws IOException {
//		File path = new File("d:\\pictures");
//		File filePath = new File(path,name);
//		FileInputStream fin = new FileInputStream(filePath);
//		picByte = new byte[(int)filePath.length()];
//		fin.read(picByte);
//		fin.close();
//				
//	}
}
