import { useState } from "react";
import axios from "axios";

function AvatarUpload() {
    const [file, setFile] = useState(null);
    const [avatarUrl, setAvatarUrl] = useState("");


    const cloudName = import.meta.env.VITE_CLOUDINARY_CLOUD_NAME;
    const uploadPreset = import.meta.env.VITE_CLOUDINARY_UPLOAD_PRESET;

    const uploadImage = async () => {
        if (!file) return;

        const formData = new FormData();
        formData.append("file", file);
        formData.append("upload_preset", uploadPreset);

        try {
            const res = await fetch(
                `https://api.cloudinary.com/v1_1/${cloudName}/image/upload`,
                {
                    method: "POST",
                    body: formData,
                }
            );

            const data = await res.json();

            if (data.secure_url) {
                console.log("Uploaded avatar URL:", data.secure_url);
                setAvatarUrl(data.secure_url);
                await axios.post('/api/users/avatar', {
                    email: localStorage.getItem("userEmail"),
                    avatarUrl: data.secure_url
                })
                    .then(function (response) {
                        console.log(response);
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            } else {
                console.error("Upload failed:", data);
            }
        } catch (err) {
            console.error("Error uploading image:", err);
        }
    };

    return (
        <div className="space-y-4">
            <input
                type="file"
                accept="image/*"
                onChange={(e) => setFile(e.target.files[0])}
            />
            <button
                onClick={uploadImage}
                disabled={!file}
                className="px-4 py-2 bg-blue-500 text-white rounded"
            >
                Upload Avatar
            </button>

            {avatarUrl && (
                <div>
                    <p>Preview:</p>
                    <img
                        src={avatarUrl}
                        alt="User avatar"
                        className="w-20 h-20 rounded-full"
                    />
                </div>
            )}
        </div>
    );
}

export default AvatarUpload;